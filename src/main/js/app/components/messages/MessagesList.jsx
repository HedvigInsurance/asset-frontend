import React from 'react';
import styled from 'styled-components';
import Message from './Message';

const MessagesListContainer = styled.div`
    max-height: 900px;
    box-sizing: border-box;
    overflow-y: auto;
    padding: 20px;
    margin-bottom: 30px;
`;
/* eslint-disable */
const MessagesList = ({ messages, userId }) => {
    console.log(messages, userId);
    return (
        <MessagesListContainer>
            {messages &&
                messages.map(message => (
                    <Message
                        key={message.id}
                        content={message.content}
                        left={message.author === parseInt(userId, 10)}
                    />
                ))}
        </MessagesListContainer>
    );
};

export default MessagesList;
