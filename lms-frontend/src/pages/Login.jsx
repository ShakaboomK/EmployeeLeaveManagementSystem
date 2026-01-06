
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { login } from '../api/auth.js'
import { useAuth } from '../auth/useAuth.js'
import { required, minLen } from '../utils/validators.js'

export default function Login(){
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState(null)
  const nav = useNavigate()
  const { setUser } = useAuth()

  async function submit(e){
    e.preventDefault()
    const e1 = required(username)
    const e2 = required(password) || minLen(password, 6)
    if(e1 || e2){ setError(e1 || e2); return }
    try{
      const user = await login(username, password)
      setUser(user)
      nav('/')
    }catch(err){ setError(err?.response?.data?.message || 'Invalid credentials') }
  }

  return (
    <div className="container" style={{display:'grid', placeItems:'center', minHeight:'100vh'}}>
      <form className="card vstack" style={{width:420}} onSubmit={submit} aria-label="Sign in">
        <h2 style={{textAlign:'center'}}>Employee Leave Management</h2>
        <div className="vstack">
          <label className="label">Username</label>
          <input className="input" value={username} onChange={e=>setUsername(e.target.value)} placeholder="Enter your username" />
        </div>
        <div className="vstack">
          <label className="label">Password</label>
          <input type="password" className="input" value={password} onChange={e=>setPassword(e.target.value)} placeholder="Enter your password" />
        </div>
        {error && <div className="badge rejected" role="alert">{error}</div>}
        <button className="btn primary" type="submit">Sign In</button>
        <div style={{marginTop:8, color:'#6B7280', fontSize:12}}>
          <strong>Demo Credentials</strong><br/>
          Admin: <code>admin / admin123</code><br/>
          Manager: <code>manager1 / manager123</code><br/>
          Employee: <code>employee1 / emp123</code>
        </div>
      </form>
    </div>
  )
}
