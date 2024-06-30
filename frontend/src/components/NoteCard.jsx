import React from "react";
import { Card, Button } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrash } from "@fortawesome/free-solid-svg-icons";

const NoteCard = ({ note, onEdit, onDelete }) => {
  const handleDelete = () => {
    onDelete(note.id);
  };

  return (
    <Card className="mb-3">
      <Card.Body>
        <Card.Title>{note.title}</Card.Title>
        <Card.Text>{note.description}</Card.Text>
        <div className="d-flex justify-content-center gap-2">
          <Button variant="primary" onClick={() => onEdit(note)}>
            <FontAwesomeIcon icon={faEdit} /> Edit
          </Button>
          <Button variant="danger" onClick={handleDelete}>
            <FontAwesomeIcon icon={faTrash} /> Delete
          </Button>
        </div>
      </Card.Body>
    </Card>
  );
};

export default NoteCard;
