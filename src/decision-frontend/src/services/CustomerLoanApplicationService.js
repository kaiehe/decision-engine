import http from '../http-common'

class CustomerLoanApplicationService {
    apply(identityCode, amount, period) {
    return http.get('/apply')}
}

export default new CustomerLoanApplicationService()