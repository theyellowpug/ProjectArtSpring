package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Comment;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.model.CommentModel;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.CommentRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public List<Comment> getAllCommentsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        return client.getComments();
    }

    public List<CommentModel> getAllCommentByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        List<Comment> comments = product.getComments();
        List<CommentModel> commentModels = new ArrayList<>();
        for (Comment comment : comments) {
            commentModels.add(CommentModel.builder()
                    .id(comment.getId())
                    .text(comment.getText())
                    .ownerName(comment.getOwner().getProfile().getNickname())
                    .ownerId(comment.getOwner().getProfile().getId())
                    .build());
        }

        return commentModels;
    }

    public String createComment(Long clientId, Long productId, String text) {
        Client owner = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

        Comment comment = Comment.builder()
                .owner(owner)
                .product(product)
                .text(text)
                .build();

        commentRepository.save(comment);

        return owner.getEmail() + " COMMENTED " + comment.getText() + " ON " + product.getName();
    }
}
