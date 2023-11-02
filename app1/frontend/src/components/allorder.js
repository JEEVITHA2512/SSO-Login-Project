import React, { Component, Fragment, useEffect, useState } from "react";
import './allorder.css';

const centerTextStyle = {
  textAlign: 'center',
};

function OrderTable(table){
    return (
      <div className="App">
      <h1 style={centerTextStyle}>LIST OF OREDERS</h1>
      <div className="App-header">
        <table>
          <thead>
            <tr>
              <th>Item No</th>
              <th>Item Name</th>
              <th>Date of Ordered</th>
              <th>Price of 10 Items</th>
            </tr>
          </thead>
          <tbody>
            {table.table.map((item) => (
              <tr key={item.id}>
                <td>{item.name}</td>
                <td>{item.category}</td>
                <td>{item.dateOfOrder}</td>
                <td>${item.price}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
      );
}

export default function AllOrder(){
    const [data, setData] = useState(0);
    const [table,setTable] = useState([]);

    async function is_admin(){
        const response = await fetch('http://localhost:8002/check/admin',{
            headers:{
                'Authorization':'Bearer '+localStorage.getItem("bearer-token"),
            }});
        const users = await response.json();
        setData(users);
        console.log(data);
    }

    async function getOrders() {
        const response = await fetch('http://localhost:8002/api/orders');
        const users = await response.json();
        setTable(users);
        console.log(table);
      }
    
    useEffect(() => {
        is_admin();
        getOrders();
    },[]);

    console.log("table",table);
    console.log("data",data);


    return (
        <Fragment>
        {data==1 ? (<OrderTable table={table}/>) : (<h1>Not Authorized to view all products</h1>)}
        </Fragment>
    );
}