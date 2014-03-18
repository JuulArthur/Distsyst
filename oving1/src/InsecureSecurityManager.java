import java.security.AccessControlException;
import java.security.Permission;
import java.util.Hashtable;

public class InsecureSecurityManager extends SecurityManager{

    private Hashtable<Permission,Permission> gp;

    public InsecureSecurityManager(){
        gp = new Hashtable<Permission,Permission>();
    }

    @Override
    public void checkPermission(Permission permission){
        try{
            super.checkPermission(permission);
        }
        catch (AccessControlException ace){
            if (gp.get(permission) == null){
                gp.put(permission,permission);
            }
        }
    }

}
