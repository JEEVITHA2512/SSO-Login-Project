import React, { Fragment, useEffect, useState } from "react";
import './allorder.css';

const centerTextStyle = {
  textAlign: 'center',
};

function OrderTable(table){
    return (
      <div className="App">
      <h1 style={centerTextStyle}>LIST OF ORDERS</h1>
      <div className="App-header">
        <table>
          <thead>
            <tr>
              <th>Item No</th>
              <th>Item Name</th>
              <th>Date of Order</th>
              <th>Price of Item</th>
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
        const response = await fetch(`${process.env.REACT_APP_BACKEND_HOST}/check/admin`,{
            headers:{
                'Authorization':'Bearer '+localStorage.getItem("bearer-token"),
                "Access-Control-Allow-Origin": "*",
            }});
        const users = await response.json();
        setData(users);
    }

    async function getOrders() {
        const response = await fetch(`${process.env.REACT_APP_BACKEND_HOST}/api/orders`);
        const users = await response.json();
        setTable(users);
      }
    
    useEffect(() => {
        is_admin();
        getOrders();
    },[]);


    return (
        <Fragment>
        {data===1 ? (<OrderTable table={table}/>) : (<h1>Not Authorized to view all products</h1>)}
        </Fragment>
    );
}