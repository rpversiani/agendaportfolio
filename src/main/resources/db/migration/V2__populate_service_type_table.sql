INSERT INTO service_type (
    id,
    name,
    description,
    price,
    duration,
    created_at,
    updated_at
) VALUES
(
    gen_random_uuid(),
    'Liberação Miofascial',
    'Recuperação muscular por meio de liberação manual',
    60.00,
    60,
    now(),
    now()
),
(
    gen_random_uuid(),
    'Banheira de Gelo com Sauna',
    'Sessão de contraste entre quente e frio para recuperação muscular e desinflamação',
    200,
    30,
    now(),
    now()
),
(
    gen_random_uuid(),
    'Acupuntura',
    'Sessão de acupuntura tradicional',
    180.00,
    60,
    now(),
    now()
);
