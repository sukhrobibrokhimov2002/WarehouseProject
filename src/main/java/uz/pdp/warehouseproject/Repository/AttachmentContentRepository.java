package uz.pdp.warehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.attachment.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

Optional<AttachmentContent> findByAttachment_Id(Integer attachment_id);
void deleteByAttachmentId(Integer attachment_id);

}
