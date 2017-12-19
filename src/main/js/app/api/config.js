export default {
    baseUrl: 'http://localhost:8080/api/',
    asset: {
        get: {
            method: 'get',
            url: 'assets'
        },
        update: {
            method: 'post',
            url: 'assets'
        }
    },
    // TODO fix login url
    login: {
        login: {
            method: 'post',
            url: 'login'
        },
        logout: {
            method: 'post',
            url: 'logout'
        }
    }
};
