
import org.avaje.agentloader.*;
import com.avaje.ebean.*;
import com.enseval.tukarguling.model.Progress;
import java.util.*;
import com.enseval.tukarguling.util.Util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TES {

    public static void main(final String[] args) {
        AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent", "debug=1");

        List<Progress> lp = Ebean.find(Progress.class).findList();
        List<Progress> filteredProgress = Ebean.filter(Progress.class)
                .contains("noTTRB", "sdasd")
                .filter(lp);
        
        for (Progress filteredProgres : filteredProgress) {
            System.out.println(filteredProgres.getNoBKB());
        }
        
        
    }

}
