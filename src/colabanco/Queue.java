
package colabanco;

public class Queue {

    String queue;
    Node bank;

    public Queue() {
        //Setear banco como unico en la cadena
        this.bank = new Node();
        this.bank.setData(0);
        this.bank.setNext(this.bank);//y despues de el esta el mismo
    }

    //ingresar a la cola
    public void enQueue(int data) {
        //System.out.println(data);

        Node newNode = new Node();//creamos un nuevo nodo

        newNode.setData(data);//llenamos su dato
        newNode.setNext(bank);//le decimos q despues el estara el banco (es el último) y procedemos a incluirlo

        if (bank.getNext() == bank) {//si solo esta el banco en la cola
            bank.setNext(newNode);//ahora es el siguiente al banco y su siguiente es el banco
        } else {//si hay mas nodos en la cadena ademas del banco
            Node node = bank;//Ubicamos el banco
            boolean flag = false;

            while (!flag) {
                node = node.getNext();//Recorremos la cadena desde el banco

                if (node.getNext() == bank) {//hasta encontrar el que tenga como proximo al banco (el ultimo)
                    node.setNext(newNode);//rompemos la cadena y agregamos el nuevo nodo como siguiente al que tenia el banco
                    flag = true;//terminamos el bucle
                }
            }
        }
        showContent("enqueue");
    }

    //Procesar cola
    public int deQueue() {
        if (bank.getNext() != bank) {//si por lo menos hay un cliente en la cadena
            Node next = bank.getNext();//al siguiente al banco (primero en la cola)
            int data = next.getData();//le extraemos el valor
            bank.setNext(next.getNext());//y lo sacamos de la cadena

            if (next.getData() > 5) {//si tiene mas de 5 en el valor
                showContent("dequeue");
                enQueue(data-5);//vuelve a la cola con 5 menos
                return 5;
            } else {//si tiene máximo 5 en el valor
                showContent("dequeue");
                return data;
            }
        } else {//si no hay clientes en la cadena
            showContent("dequeue");
            System.out.println("empty queue");
            return 0;
        }
    }
    
    public Node getBank() {
        return bank;
    }

    public void showContent(String action) {//solo se usa para imprimir en consola
        queue = "";
        Node user = bank.getNext();
        String invertedQueue = "";

        while (user != bank) {
            queue += user.getData() + " ";
            user = user.getNext();
        }

        String[] string = queue.split(" ");

        for (int i = string.length - 1; i >= 0; i--) {
            invertedQueue += " " + string[i];
        }
        
        System.out.println(action + " " + invertedQueue);
    }
}
