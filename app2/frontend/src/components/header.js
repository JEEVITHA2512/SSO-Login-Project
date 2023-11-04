import React, { useEffect, useState } from "react";
import $ from 'jquery';
import { Link } from "react-router-dom";

export default function Header(){
        const [data, setData] = useState(0);
      
        useEffect(()=> {
          $.ajax({
            type: "GET",
            url: "http://localhost:8004/check/admin",
            headers : {
               'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")
            },
            success: function (response) {
              setData(response);
            },
            error: function () {
              console.log("Error loading data");
              setData(0);
            }
          });
        }, []);
      
        
        return (
          <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/user/profile">My Profile</Link></li>
          {data==1 ? (<li><Link to="/user/all">All Users</Link></li>):(<p></p>)}
          </ul>
        );
      }
