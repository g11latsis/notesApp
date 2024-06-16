package gr.aueb.NoteApp.service.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super(entityClass.getSimpleName() + " with id " + id + " not found");
    }
}
