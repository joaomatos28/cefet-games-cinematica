package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;


public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'c';
    float raio;
    float timeToTarget;
    
    public Chegar(float maxVelocidade,float raio,float timeToTarget) {
        this(NOME, maxVelocidade,raio,timeToTarget);
        
    }

    protected Chegar(char nome, float maxVelocidade,float raio,float timeToTarget) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
        this.raio = raio;
        this.timeToTarget = timeToTarget;
        
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();
        
        output.velocidade = new Vector3(super.alvo.getObjetivo().x - agente.posicao.x,
                                        super.alvo.getObjetivo().y - agente.posicao.y,
                                        super.alvo.getObjetivo().z - agente.posicao.z);
        
        if(output.velocidade.len()<raio) {
            output.velocidade.set(0, 0, 0);
            return output;
        }
        
        output.velocidade.scl(1/timeToTarget);
        output.velocidade.nor();
        output.velocidade.scl(maxVelocidade);
        output.velocidade.nor().scl(maxVelocidade);
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        
        

        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }
}
