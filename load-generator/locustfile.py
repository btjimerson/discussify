#!/usr/bin/python

import random
from locust import HttpUser, between, task

postIds = [
    "67595c74-61f3-4bf4-8d57-43a82e39283e",
    "3040df0e-af71-43de-8d30-fed78915ceee",
    "924c8664-566e-4d43-ba72-ad659b6b1765"
]

class WebSiteUser(HttpUser):
    wait_time = between(0.1, 0.5)

    @task
    def index(self):
        self.client.get("/")
    
    @task
    def getPost(self):
        self.client.get("/posts/" + random.choice(postIds))
