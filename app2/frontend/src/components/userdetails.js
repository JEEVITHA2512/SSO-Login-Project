import React, { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";

export default function UserDetails(){
    let username = jwtDecode(localStorage.getItem("bearer-token")).preferred_username
    const [data, setData] = useState(0)

    let url = `${process.env.REACT_APP_BACKEND_HOST}/get/user`

    let params = {"username":username, };

    
    async function fetch_users(){
        const response = await fetch(url,{
            method:"POST",
            headers:{
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(params),});
        const users = await response.text();
        setData(JSON.parse(users)[0]);
    };

    useEffect(() => {
        fetch_users();
    },[]);


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