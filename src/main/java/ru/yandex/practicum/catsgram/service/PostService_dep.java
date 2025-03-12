package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

// Указываем, что класс PostService - является бином и его
// нужно добавить в контекст приложения
@Service
public class PostService_dep {
    private final Map<Long, Post> posts = new HashMap<>();
    private final UserService_dep userService;

    public PostService_dep(UserService_dep userService) {
        this.userService = userService;
    }

    public Collection<Post> findAll(int size, String sort, int from) {
        return posts.values().stream()
                .filter(post -> (post.getId() > from) && (post.getId() <= from + size))
                .sorted(SortOrder.DESCENDING.equals(SortOrder.from(sort)) ?
                        Comparator.comparing(Post::getId).reversed() : Comparator.comparing(Post::getId))
                .collect(Collectors.toList());

    }

    public Post create(Post post) {
        if (post.getDescription() == null || post.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }
     /*   if (userService.findOptUserById(post.getAuthorId()).isEmpty()) {
            throw new ConditionsNotMetException("Автор с id = " + post.getAuthorId() + " не найден");
        }*/

        post.setId(getNextId());
        post.setPostDate(Instant.now());
        posts.put(post.getId(), post);
        return post;
    }

    public Post update(Post newPost) {
        if (newPost.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (posts.containsKey(newPost.getId())) {
            Post oldPost = posts.get(newPost.getId());
            if (newPost.getDescription() == null || newPost.getDescription().isBlank()) {
                throw new ConditionsNotMetException("Описание не может быть пустым");
            }
            oldPost.setDescription(newPost.getDescription());
            return oldPost;
        }
        throw new NotFoundException("Пост с id = " + newPost.getId() + " не найден");
    }

    public Optional<Post> findOptPostById(long postId) {
        return posts.values().stream()
                .filter(p -> p.getId().equals(postId))
                .findFirst();
    }

    public Post findPostById(long postId) {
        return findOptPostById(postId)
                .orElseThrow(() -> new NotFoundException(String.format("Пост № %d не найден", postId)));
    }

    private long getNextId() {
        long currentMaxId = posts.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    public enum SortOrder {
        ASCENDING, DESCENDING;

        // Преобразует строку в элемент перечисления
        public static SortOrder from(String order) {
            switch (order.toLowerCase()) {
                case "ascending":
                case "asc":
                    return ASCENDING;
                case "descending":
                case "desc":
                    return DESCENDING;
                default: return null;
            }
        }
    }
}