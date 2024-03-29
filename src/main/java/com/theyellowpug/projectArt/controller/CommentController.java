package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Comment;
import com.theyellowpug.projectArt.model.CommentModel;
import com.theyellowpug.projectArt.model.ProductModel;
import com.theyellowpug.projectArt.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/allByClientId")
    public ResponseEntity<List<Comment>> getAllCommentsByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(commentService.getAllCommentsByClientId(clientId));
    }

    @GetMapping("/allByProductId")
    public ResponseEntity<List<CommentModel>> getAllCommentsByProductId(@RequestParam("productId") Long productId) {
        return ResponseEntity.ok(commentService.getAllCommentByProductId(productId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createComment(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId, @RequestParam String text) {
        return ResponseEntity.ok(commentService.createComment(clientId, productId, text));
    }
}
