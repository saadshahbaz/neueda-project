import Vue from 'vue'
import VueRouter from 'vue-router'
import dashboard from '../views/Dashboard'
import landingPage from '../components/LandingPage'
import customerDashboard from "../views/customerDashboard";
import assistantDashboard from "../views/assistantDashboard";
import * as path from "path";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: landingPage
  },
  {
    path: '/home',
    name: 'home',
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
  },
  {
    path: '/customerHome',
    name: 'customerHome',
    component: customerDashboard,
    children: [
      {
        path: '',
        component: () => import(/* webpackChunkName: "Overview" */ '../views/Profile.vue')
      },
      {
        path: 'informationUpdate',
        component: () => import(/* webpackChunkName: "Profile" */ '../views/informationUpdate.vue')
      },
      {
        path: 'car',
        component: () => import(/* webpackChunkName: "Profile" */ '../views/Car.vue')
      },
      {
        path: 'customerAppointment',
        component: () => import(/* webpackChunkName: "Messages" */ '../views/customerAppointment.vue'),
      },
      {
        path: 'services',
        component: () => import('../views/CustomerServices.vue'),
      },
      {
        path: 'bill',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/Bill.vue')
      },
      {
        path: 'deleteAccount',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/DeleteAccount.vue')
      },
    ]
  },
  {
    path: '/assistantHome',
    name: 'assistantHome',
    component: assistantDashboard,
    children: [
      {
        path: '',
        component: () => import(/* webpackChunkName: "Overview" */ '../views/Profile.vue')
      },

      {
        path: 'business',
        component: () => import(/* webpackChunkName: "Messages" */ '../views/Business.vue')
      },
      {
        path: 'space',
        component: () => import(/* webpackChunkName: "Messages" */ '../views/Space.vue')
      },
      {
        path: 'technicians',
        component: () => import(/* webpackChunkName: "Messages" */ '../views/Technicians.vue')
      },
      {
        path: 'service',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/Services.vue')
      },
      {
        path: 'viewAppointments',
        component: () => import(/* webpackChunkName: "Settings" */ '../views/ViewAppointments.vue')
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
