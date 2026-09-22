package cl.bitss.Pagos.service;
import cl.bitss.Pagos.model.Pago;
import cl.bitss.Pagos.repository.PagoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    private final PagoRepository repository;
    public PagoService(PagoRepository repository) {
        this.repository = repository;
    }

    public List<Pago> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Pago> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Pago crear(Pago pago) {
        return repository.save(pago);
    }

    public Optional<Pago> actualizar(Long id, Pago datos) {
        Optional<Pago> pagoEncontrado = repository.findById(id);
        if (pagoEncontrado.isPresent()) {
            Pago pago = pagoEncontrado.get();
            pago.setPedidoId(datos.getPedidoId());
            pago.setUsuarioId(datos.getUsuarioId());
            pago.setMonto(datos.getMonto());
            pago.setMetodoPago(datos.getMetodoPago());
            pago.setEstado(datos.getEstado());
            return Optional.of(repository.save(pago));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Pago> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Pago> obtenerPorPedido(Long pedidoId) {
        return repository.findByPedidoId(pedidoId);
    }
}