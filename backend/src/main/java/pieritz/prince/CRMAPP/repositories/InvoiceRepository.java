package pieritz.prince.CRMAPP.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pieritz.prince.CRMAPP.domain.Invoice;

@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
  Page<Invoice> findByKontaktId(Long kontaktId, Pageable pageable);
  long countByStatus(String status);
  Page<Invoice> findByLeistungsbezeichnung(String leistungsbezeichnung, Pageable pageable);

}
