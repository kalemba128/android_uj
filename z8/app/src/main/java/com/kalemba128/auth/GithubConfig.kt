package com.kalemba128.auth

class GithubConfig {
    companion object {
        val clientId = ""
        val clientSecret = ""
        val redirectUri = "z8://redirect"
        val baseUrl = "https://github.com/"
        val apiUrl = "https://api.github.com/"

        val oauth2Url =
            "https://github.com/login/oauth/authorize?client_id=${clientId}&scope=user&redirect_uri=${redirectUri}";
    }
}

