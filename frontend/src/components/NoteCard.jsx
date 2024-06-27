import React  from "react";
import { Card, Button } from "react-bootstrap";

const NoteCard = ({ note, onEdit, onDelete }) => {
  
  const handleDelete = () => {
    onDelete(note.id);
  };

  return (
    <Card className="mb-3">
      <Card.Body>
        <Card.Title>{note.title}</Card.Title>
        <Card.Text>{note.description}</Card.Text>
        <Button variant="primary" onClick={() => onEdit(note)}>
          Edit
        </Button>
        <Button variant="danger" onClick={handleDelete}>Delete</Button>
      </Card.Body>
    </Card>
  );
};

export default NoteCard;
