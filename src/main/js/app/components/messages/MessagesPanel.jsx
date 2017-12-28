import React from 'react';
import { Form } from 'semantic-ui-react';

/* eslint-disable react/prop-types */
const MessagesList = ({ addMessage }) => (
    <Form reply>
      <Form.TextArea onKeyPress={(e) => {
          if (e.key === 'Enter') {
              addMessage(e.target.value, 'Me')
          }
      }} />
    </Form>
);

export default MessagesList;
