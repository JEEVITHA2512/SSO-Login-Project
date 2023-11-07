import React from 'react';
import ReactDOM from 'react-dom';
import Keycloak from 'keycloak-js';
import $ from "jquery";



//keycloak init options
let initOptions = {
    url: 'http://localhost:8080/', 
    realm: 'sso-login', 
    clientId: 'react-login-client-2', 
    onLoad: 'login-required'
}

let keycloak = new Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).then((auth) => {

    if (!auth) {
        window.location.reload();
    } else {
        console.info("Authenticated");
    }

    localStorage.setItem("bearer-token", keycloak.token);
    localStorage.setItem("refresh-token", keycloak.refreshToken);

    setTimeout(() => {
        keycloak.updateToken(70).then((refreshed) => {
            if (refreshed) {
                console.debug('Token refreshed' + refreshed);
            } else {
                console.warn('Token not refreshed, valid for '
                    + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
            }
        }).catch(() => {
            console.error('Failed to refresh token');
        });


    }, 60000)

}).catch(() => {
    console.error("Authenticated Failed");
});



function LogOut(){
    const logout = ()=>{
        keycloak.logout();
    };

    return (
     <>
        <button  onClick={logout}>
          Logout
        </button>
    </>

    );
}

ReactDOM.render(<LogOut />, document.getElementById("logoutBtn"));



function CheckRoles() {
  const check = () => {
    $.ajax({
      type: "GET",
      url: "http://localhost:8004/check/admin",
      headers : {
         'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")
      },
      success: function (data1) {
        document.getElementById("read").innerHTML = data1;
      },
      error: function (error) {
        document.getElementById("read").innerHTML = "You dont have admin privilages";
      }
    });

    $.ajax({
      type: "GET",
      url: "http://localhost:8004/check/customer",
      headers : {
         'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")
      },
      success: function (data2) {
        document.getElementById("write").innerHTML = data2;
      },
      error: function (error) {
        document.getElementById("write").innerHTML = "You dont have customer privilages";
      }
    });


  };

  return (
    <>
      <p> Check Admin privilages at <strong>/check/admin</strong> and check Customer privilages at <strong>/check/customer</strong>
      </p>
      <button onClick={check} >
        Check 
      </button>
      <p id="read"> </p>
      <p id="write"> </p>
    </>
  );
}
ReactDOM.render(<CheckRoles/>, document.getElementById("root"));