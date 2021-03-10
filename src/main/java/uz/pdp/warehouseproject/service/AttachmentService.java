package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseproject.Repository.AttachmentContentRepository;
import uz.pdp.warehouseproject.Repository.AttachmentRepository;
import uz.pdp.warehouseproject.entity.attachment.Attachment;
import uz.pdp.warehouseproject.entity.attachment.AttachmentContent;
import uz.pdp.warehouseproject.payload.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    //Method for uploading
    public Result uploadImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());

        //checking whether file exist or not in database
        boolean checkFile = attachmentRepository.existsByOriginalName(file.getOriginalFilename());
        if (checkFile) return new Result("File already exists", true);


        Attachment attachment = new Attachment();
        if (file == null) return new Result("Error loading picture", false);
        attachment.setOriginalName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        attachment.setSize((int) file.getSize());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        //Working with attachment bytes
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setMainCode(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("File successfully uploaded= " + savedAttachment.getId(), true);

    }

    //method for downloading
    public void download(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return;
        Attachment attachment = attachmentOptional.get();

        Optional<AttachmentContent> byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);
        if (!byAttachment_id.isPresent()) return;
        AttachmentContent attachmentContent = byAttachment_id.get();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getOriginalName() + "\"");
        response.setContentType(attachment.getContentType());

        FileCopyUtils.copy(attachmentContent.getMainCode(), response.getOutputStream());


    }


    //Deleting picture
    public Result delete(Integer id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return new Result("Attachment not found", false);
        Optional<AttachmentContent> byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);
        if (!byAttachment_id.isPresent()) return new Result("Attachment content not found", false);
        AttachmentContent attachmentContent = byAttachment_id.get();
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new Result("Attachment successfully deleted", true);
    }

    public Page<Attachment> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return attachmentRepository.findAll(pageable);
    }

    public Attachment getOneById(Integer id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return new Attachment();
        return attachmentOptional.get();
    }

    public Result edit(Integer id, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return new Result("Attachment not found", false);
        if (file == null) return new Result("Eror in loading image", false);

        //
        Optional<AttachmentContent> byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);
        if (!byAttachment_id.isPresent()) return new Result("Error in loading", false);

        Attachment attachment = attachmentOptional.get();
        attachment.setSize((int) file.getSize());
        attachment.setOriginalName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        Attachment save = attachmentRepository.save(attachment);


        //Working with bytes
        AttachmentContent attachmentContent = byAttachment_id.get();
        attachmentContent.setAttachment(save);
        attachmentContent.setMainCode(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("Successfully edited", true);

    }
}
