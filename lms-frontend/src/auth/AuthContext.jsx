
import React, { createContext, useEffect, useState } from 'react'
import { me, logout as apiLogout } from '../api/auth.js'
export const AuthContext = createContext({ user: null, setUser: () => {}, logout: () => {} })
export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null)
  useEffect(() => { (async () => { try { const u = await me(); setUser(u) } catch {} })() }, [])
  const logout = () => { apiLogout(); setUser(null) }
  return <AuthContext.Provider value={{ user, setUser, logout }}>{children}</AuthContext.Provider>
}
