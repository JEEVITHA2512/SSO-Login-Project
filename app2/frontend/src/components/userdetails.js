import React, { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";

export default function UserDetails(){
    let username = jwtDecode(localStorage.getItem("bearer-token")).preferred_username
    console.log("username",username)
    const [data, setData] = useState(0)

    let url = 'http://localhost:8004/get/user'

    let params = {"username":username, };

    
    async function fetch_users(){
        const response = await fetch(url,{
            method:"POST",
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(params),});
        const users = await response.text();
        console.log("s",users);
        setData(JSON.parse(users)[0]);
        console.log(data);
    };

    useEffect(() => {
        fetch_users();
    },[]);

    console.log("v",data);

    return (
        <div className='box'>
          <h1>{data.username} Details</h1>
          <div>
            User Name: {data.username} <br/>
            First Name: {data.firstName} <br/>
            Last Name: {data.lastName} <br/>
            Email: {data.email} <br/>
          </div>
        </div>
      );
}