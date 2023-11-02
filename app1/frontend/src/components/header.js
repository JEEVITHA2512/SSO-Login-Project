import React, { useEffect, useState } from "react";
import $ from 'jquery';
import { Link } from "react-router-dom";

export default function Header(){
        const [data, setData] = useState(0);
      
        useEffect(()=> {
          $.ajax({
            type: "GET",
            url: "http://localhost:8002/check/admin",
            headers : {
               'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")
            },
            success: function (response) {
              setData(response);
            },
            error: function () {
              console.log("Error loading data");
            }
          });
        }, []);
      
        
        return (
          <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/order/new">Order</Link></li>
          {data==1 ? (<li><Link to="/order/all">List of all orders</Link></li>):(<p></p>)}
          </ul>
        );
      }
