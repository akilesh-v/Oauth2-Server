CREATE TABLE IF NOT EXISTS public.users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    role     VARCHAR(255) NOT NULL,
    enabled  BOOLEAN      NOT NULL default false,
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.posts
(
    id             SERIAL PRIMARY KEY,
    user_id        BIGINT,
    post_data      VARCHAR(255),
    no_of_likes    INTEGER,
    no_of_comments INTEGER,
    CONSTRAINT post_user_id_fk FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
);