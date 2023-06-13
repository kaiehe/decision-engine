import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/apply/:identityCode/:amount/:period',
            name: 'loan-application',
            component: () => import('./components/LoanApplication')
        }
    ]
})