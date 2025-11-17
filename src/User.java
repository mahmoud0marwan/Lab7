public abstract class User {
    protected String userId;
    protected String username;
    protected String email;
    protected String passwordHash;
    protected String role;

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    boolean authenticate(String password)
    {
        String passwordH=AuthManager.hashPassword(password);
        if (this.passwordHash.equals(passwordH))
            return true;
        return false;
    }
    public void setRole(String role){ this.role = role; }

    abstract String getRole();
}
