
import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../auth/useAuth.js'
import logo from '../assets/logo.svg'
export default function Navbar(){
  const { user, logout } = useAuth()
  const nav = useNavigate()
  return (
    <nav className="card hstack" aria-label="Main navigation" style={{justifyContent:'space-between', marginBottom:16}}>
      <div className="hstack" style={{gap:16}}>
        <img src={logo} alt="Company logo" width={28} height={28} />
        <strong>Employee Leave Management</strong>
        <Link to="/" className="btn ghost">Dashboard</Link>
        {user?.role === 'MANAGER' && <Link to="/manager" className="btn ghost">Manager</Link>}
        {user?.role === 'ADMIN' && <Link to="/admin" className="btn ghost">Admin</Link>}
      </div>
      <div className="hstack">
        {user && <span aria-live="polite">Welcome, {user.name}</span>}
        {user && (
          <button className="btn ghost" onClick={() => { logout(); nav('/login') }}>
            Logout
          </button>
        )}
      </div>
    </nav>
  )
}
