package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Jogo;
import modelo.Time;

public class DAOJogo extends DAO<Jogo> {

    public Jogo read(Object chave) {
        int id = (Integer) chave;
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.descend("id").constrain(id);
        List<Jogo> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }
    
    public void setID(Jogo jogo, int id) {
        jogo.setId(id);
    }

    public List<Jogo> buscarPorTime(Time time) {
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.descend("time1").constrain(time).or(q.descend("time2").constrain(time));
        return q.execute();
    }

    public List<Jogo> buscarPorData(String data) {
        Query q = manager.query();
        q.constrain(Jogo.class);
        q.descend("data").constrain(data);
        return q.execute();
    }

    public List<Jogo> jogosComIngressosEsgotados() throws Exception {
        DAOJogo daoJogo = new DAOJogo();
        List<Jogo> jogos = daoJogo.jogosComIngressosEsgotados();
        if (jogos.isEmpty()) {
            throw new Exception("Não existem jogos com ingressos esgotados.");
        }
        return jogos;
    }
    
    
}
