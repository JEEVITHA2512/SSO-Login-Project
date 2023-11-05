import React, { useEffect, useState } from 'react';
import keycloak from './keycloak-init';
import $ from "jquery";

const centerTextStyle = {
    textAlign: 'center',
  };

function UserTable(tabledata){
    console.log(tabledata)
    if (tabledata.tabledata==0) return (<></>)
    return (
        <div className="App">
          <h1 style={centerTextStyle}>User Details</h1>
          <div className="App-header">
            <table>
              <thead>
                <tr>
                  <th>Id</th>
                  <th>User Name</th>
                  <th>Full Name</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                {tabledata.tabledata.map((item, index) => (
                  <tr key={index}>
                    <td>{index+1}</td>
                    <td>{item.username}</td>
                    <td>{item.firstName +" "+ item.lastName}</td>
                    <td>{item.email}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
        
      );
}

export default function UserList(){
    const [data, setData] = useState(0);
    const [admin, setAdmin] = useState(0);

    let url = 'http://localhost:8080/admin/realms/sso-login/users'

    
    async function fetch_users(){
        const response = await fetch(url,{
            headers:{
                'Authorization':'Bearer '+localStorage.getItem("bearer-token"),
            }});
        const users = await response.json();
        setData(users);
        console.log(data);
    };

    useEffect(() => {
        fetch_users();
        $.ajax({
          type: "GET",
          url: "http://localhost:8004/check/admin",
          headers : {
             'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")
          },
          success: function (response) {
            setAdmin(response);
          },
          error: function () {
            console.log("Error loading data");
            setData(0);
          }
        });
    },[]);

    console.log("v",data);

    return (admin==0 ? (<h1>Not Authorized</h1>) : (<UserTable tabledata={data}/>))
}