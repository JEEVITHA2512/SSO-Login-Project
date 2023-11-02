import React from "react";
import keycloak from "./keycloak-init";

export default function Footer(){
    
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