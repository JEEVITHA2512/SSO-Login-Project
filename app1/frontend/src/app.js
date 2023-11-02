import React, { Fragment, useEffect } from "react";
import $ from "jquery";
import { useState } from "react";

import { Routes, Route, Link, BrowserRouter as Router } from "react-router-dom";

function Home(){
  return (<h1>Home</h1>);
}

function Order(){
  return (<h1>Order</h1>);
}

function AllOrder(){
  return (<h1>All Order</h1>);
}

export default function App() {
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
  <Router>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/order/new">Order</Link></li>
          {data==1 ? (<li><Link to="/order/all">List of all orders</Link></li>):(<p></p>)}
        </ul>

        <Routes>
        <Route path="/" exact component={Home} />
        <Route path="/order/new"  component={Order} />
        <Route path="/order/all"  component={AllOrder} />
        </Routes>
</Router>
  );
}
