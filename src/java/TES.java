
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

        Progress p = new Progress();
        p.setNamaChecker("SCASD");
        p.setNamaDirExp("asdas");
        p.setNamaOutlet("asdas");
        p.setNamaPenerimaKembali("asdas");
        p.setNoBKB("asdas");
        p.setNoTTRB("asdas");
        p.setTglBKB(new Date());
//        p.setWaktuKembali(new Timestamp(new Date().getTime()));
//        p.setWaktuTerimaChecker(new Timestamp(new Date().getTime()));
//        p.setWaktuTerimaDirExp(new Timestamp(new Date().getTime()));
        Ebean.save(p);

    }

}
