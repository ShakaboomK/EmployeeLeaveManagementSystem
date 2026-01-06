
import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider } from './auth/AuthContext.jsx'
import PrivateRoute from './auth/PrivateRoute.jsx'
import Login from './pages/Login.jsx'
import EmployeeDashboard from './pages/EmployeeDashboard.jsx'
import ManagerDashboard from './pages/ManagerDashboard.jsx'
import AdminDashboard from './pages/AdminDashboard.jsx'

export default function App(){
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          {/* <Route path="/login" element={<Login/>} />
          <Route path="/" element={<PrivateRoute><EmployeeDashboard/></PrivateRoute>} />
          <Route path="/manager" element={<PrivateRoute allow={["MANAGER"]}><ManagerDashboard/></PrivateRoute>} />
          <Route path="/admin" element={<PrivateRoute allow={["ADMIN"]}><AdminDashboard/></PrivateRoute>} />
          <Route path="*" element={<Navigate to="/" />} /> */}

          
          <Route path="/login" element={<Login/>} />
          <Route path="/" element={<EmployeeDashboard/>} />
          <Route path="/manager" element={<ManagerDashboard/>} />
          <Route path="/admin" element={<AdminDashboard/>} />

        </Routes>
      </AuthProvider>
    </BrowserRouter>
  )
}
