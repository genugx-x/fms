db.createUser(
    {
        user: "api-test",
        pwd: "api-test",
        roles: [
            {
                role: "readWrite",
                db: "api-test"
            }
        ]
    }
);