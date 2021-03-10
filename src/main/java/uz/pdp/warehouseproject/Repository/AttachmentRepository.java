package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.attachment.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
boolean existsByOriginalName(String originalName);
}
