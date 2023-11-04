import Layout from './components/layout';
import ReactDOM from 'react-dom/client';
import { Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from 'react-router-dom';
import Home from './components/home';
import UserDetails from './components/userdetails';
import UserList from './components/userlist';

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route path='/' element={<Layout/>}>
            <Route path='' element={<Home/>}/>
            <Route path='user/profile' element={<UserDetails/>}/>
            <Route path='user/all' element={<UserList/>}/>
        </Route>
    )
)

ReactDOM.createRoot(document.getElementById('root')).render(
    <RouterProvider router={router}/>
)