package internet.shop.model;

public class Role {
    private Long roleId;
    private Enum<RoleType> roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Enum<RoleType> getRoleName() {
        return roleName;
    }

    public void setRoleName(Enum<RoleType> roleName) {
        this.roleName = roleName;
    }
}
