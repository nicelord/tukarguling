package com.enseval.tukarguling.vm;

import com.enseval.tukarguling.util.AuthenticationServiceImpl;
import org.zkoss.bind.*;
import java.util.*;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.*;

public class NavigationVM
{
    @Command
    public void changePage(@BindingParam("page") final String page) {
        final Map map = new HashMap();
        map.put("page", page);
        BindUtils.postGlobalCommand((String)null, (String)null, "doChangePage", map);
    }
    
    @Command
    public void logout() {
        new AuthenticationServiceImpl().logout();
        Executions.sendRedirect("/login.zul");
    }
}
