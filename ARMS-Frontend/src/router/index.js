import Vue from 'vue'
import VueRouter from 'vue-router'
import dashboard from '../views/Dashboard'
import landingPage from '../components/LandingPage'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: landingPage
  },
  {
    path: '/home',
    component: dashboard,
    children: [
      {
        path: '',
        component: () => import(/* webpackChunkName: "Overview" */ '../views/Business.vue')
      },
      {
        path: 'services',
        component: () => import(/* webpackChunkName: "Messages" */ '../views/Services.vue')
      },
      {
        path: 'space',
        component: () => import(/* webpackChunkName: "Profile" */ '../views/Space.vue')
      },
      {
        path: 'technicians',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/Technicians.vue')
      },
      {
        path: 'appointments',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/Appointments.vue')
      },
      {
        path: 'reminders',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/Reminders.vue')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
