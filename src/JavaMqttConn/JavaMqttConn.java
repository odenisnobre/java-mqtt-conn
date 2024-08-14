package JavaMqttConn;
import java.util.logging.Level;
import java.util.logging.Logger;

// importa bibliotecas ao projetos
import org.eclipse.paho.client.mqttv3.*;

public class JavaMqttConn {
    public static void main(String[] args) {
        // definição de URL, do broker MQTT
        String serverURI = "tcp://192.168.1.75:18883";
        // define o client id para identificação da conexão pelo Broker
        String clientId = "java_1";
        // todo código de interação com a biblioteca MQTT obrigatoriamenteo
        // deve ficar dentro uma rotina try/catch
        try {
            // cria objeto cliente MQTT
            MqttClient mqclient = new MqttClient(serverURI, clientId);            
            // cria objeto de opções de conexão do Broker MQTT
            MqttConnectOptions opts = new MqttConnectOptions();            
            // define usuario para conexão
            opts.setUserName("mqtt_user");            
            // define senha para conexão
            // o método setPassword, utiliza o array de char, por isso a conversão .toCharArray
            opts.setPassword("iftm2024".toCharArray());            
            // faz conexão com o Broker com o método Connect passando o objeto do Tipo MqttConnectOptions
            mqclient.connect(opts);            
            // realiza verificação da conexão
            
            // definição de mensagem para ser publicada
            // define topíco a ser publicado a mensagem
            String topico = "/teste/poo";
            // cria objeto de mensagem MQTT para o Broker
            MqttMessage msg = new MqttMessage();
            // seta payload(que é carga da mensagem, ou seja, a mensagem a ser enviada) e
            // obtem os bytes da mensagem, padrão da mensagem MQTT
            msg.setPayload("Teste Envio Mensagem 13".getBytes());
            // todo código de interação com o biblioteca MQTT obrigatoriamente
            // deve ficar dentro de uma rotina try/catch
            if(mqclient.isConnected()){
                // se estiver conectado mostra mensagem de sucesso
                System.out.println("Conectado!!!");
                // se estiver conectado publica mensagem;
                mqclient.publish(topico, msg);
                mqclient.disconnect();
            } else {
                // se não estiver conectado mostra mensagem de não sucesso
                System.out.println("Não conectado!!!");
            }
        } catch (MqttException ex) {
            // caso ocorra uma falha uma exceção será lançada no código
            Logger.getLogger(JavaMqttConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}