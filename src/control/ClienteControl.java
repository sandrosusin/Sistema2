/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteDao;
import model.domain.Cliente;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author sandro
 */
public final class ClienteControl {
    
    private PropertyChangeSupport propertyChangeSuport = new PropertyChangeSupport(this);
    private Cliente clienteDigitado;
    private Cliente clienteSelecionado;
    private List<Cliente> clientesTabela;
    private ClienteDao clienteDao;
    
    public ClienteControl(){
        clienteDao = new ClienteDao();
        clientesTabela = ObservableCollections.observableList(new ArrayList<Cliente>());
        novo();
        pesquisar();
    }
    public void novo() {
        setClienteDigitado(new Cliente());
    }
    public void pesquisar() {
        clientesTabela.clear();
        clientesTabela.addAll(clienteDao.Pesquisar(clienteDigitado));
    }
    public void salvar(){
      clienteDao.salvarAtualizar(clienteDigitado);
      novo();
      pesquisar();
    }
    public void excluir(){
        clienteDao.Excluir(clienteDigitado);
        novo();
        pesquisar();
    }
    public Cliente getClienteDigitado() {
        return clienteDigitado;
    }
    public void setClienteDigitado(Cliente clienteDigitado) {
        Cliente oldClienteDigitado = this.clienteDigitado;
        this.clienteDigitado = clienteDigitado;
        propertyChangeSuport.firePropertyChange("clienteDigitado", oldClienteDigitado, clienteDigitado);
    }
    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
        if(this.clienteSelecionado !=null){
            setClienteDigitado(clienteSelecionado);
        }
    }
    public List<Cliente> getClientesTabela() {
        return clientesTabela;
    }

    public void setClientesTabela(List<Cliente> clientesTabela) {
        this.clientesTabela = clientesTabela;
    }
    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
        propertyChangeSuport.addPropertyChangeListener(propertyChangeListener);
        
    }
    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener){
        propertyChangeSuport.removePropertyChangeListener(propertyChangeListener);
    }
}
