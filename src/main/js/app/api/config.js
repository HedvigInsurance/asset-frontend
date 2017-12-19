export default {
    baseUrl: 'http://localhost:8080/api/',
    asset: {
        get: {
            method: 'get',
            url: 'api/assets'
        },
        update: {
            method: 'post',
            url: 'api/assets'
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
