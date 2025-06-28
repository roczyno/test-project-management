import { Routes, Route, Navigate } from 'react-router-dom'
import { useAuth } from './contexts/AuthContext'
import Layout from './components/Layout'
import LandingPage from './pages/LandingPage'
import LoginPage from './pages/auth/LoginPage'
import RegisterPage from './pages/auth/RegisterPage'
import DashboardPage from './pages/DashboardPage'
import ProjectsPage from './pages/projects/ProjectsPage'
import ProjectDetailPage from './pages/projects/ProjectDetailPage'
import IssuesPage from './pages/issues/IssuesPage'
import IssueDetailPage from './pages/issues/IssueDetailPage'
import ProfilePage from './pages/ProfilePage'
import AcceptInvitationPage from './pages/AcceptInvitationPage'
import LoadingSpinner from './components/ui/LoadingSpinner'

function App() {
  const { user, loading } = useAuth()

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <LoadingSpinner size="lg" />
      </div>
    )
  }

  return (
    <Routes>
      {/* Public routes */}
      <Route 
        path="/" 
        element={!user ? <LandingPage /> : <Navigate to="/dashboard" replace />} 
      />
      <Route 
        path="/login" 
        element={!user ? <LoginPage /> : <Navigate to="/dashboard" replace />} 
      />
      <Route 
        path="/register" 
        element={!user ? <RegisterPage /> : <Navigate to="/dashboard" replace />} 
      />
      <Route path="/accept_invitation" element={<AcceptInvitationPage />} />
      
      {/* Protected routes */}
      <Route 
        path="/dashboard" 
        element={user ? <Layout /> : <Navigate to="/" replace />}
      >
        <Route index element={<DashboardPage />} />
      </Route>
      
      <Route 
        path="/projects" 
        element={user ? <Layout /> : <Navigate to="/" replace />}
      >
        <Route index element={<ProjectsPage />} />
        <Route path=":id" element={<ProjectDetailPage />} />
      </Route>
      
      <Route 
        path="/issues" 
        element={user ? <Layout /> : <Navigate to="/" replace />}
      >
        <Route index element={<IssuesPage />} />
        <Route path=":id" element={<IssueDetailPage />} />
      </Route>
      
      <Route 
        path="/profile" 
        element={user ? <Layout /> : <Navigate to="/" replace />}
      >
        <Route index element={<ProfilePage />} />
      </Route>
      
      {/* Catch all route */}
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  )
}

export default App