package lt.pra_va.user.model;

public enum AuthorityType {
    USER_AUTHORITY("User"),
    ADMIN_AUTHORITY("Admin");

    private final String translation;

    AuthorityType(final String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation;
    }
}
