# goit-java-dev-mod15-hw-todo-notes-spring-security

Як основу візьми проєкт з попереднього ДЗ. Поточне ДЗ можна виконувати в цьому ж репозиторії, або створити новий репозиторій - це на твій розсуд.

## Завдання №1 - додай модуль **Spring Security**
Додай до проєкту модуль **Spring Security** (достатньо додати стартер `spring-boot-starter-security`).

Запусти проєкт, і переконайсь, що модуль коректно підключивсь, і в логах запуску програми виводиться згенерований пароль. Переконайсь, що при заході у веб-інтерфейс тебе спочатку перекидає на сторінку авторизації, де тобі необхідно ввести логін та пароль, що виводивсь в консоль.

## Завдання №2 - встанови свій логін та пароль
Використовувати автозгенерований пароль кожного разу незручно.

Задай пароль `default` (наприклад, використовуючи файл `application.properties`).

Як результат, ти маєш мати можливість зайти у власну програму, використовуючи логін `user` та пароль `default`.

## Завдання №2.1 (необов'язкове)
Додай авторизацію у проєкт за допомогою **JDBC**. Додай міграцію, яка б додала користувача user з паролем `jdbcDefault`

## Завдання №3 - залий код на **Github**
Залий код на **Github** репозиторій. Переконайсь, що файл `.gitignore` налаштований коректно, і в репозиторій потрапили лише потрібні файли.

Результат ДЗ - посилання на репозиторій.

---

When Spring Security started it's looking for implementation of `SecurityFilterChain`
as default Spring bean declaration is `SecurityFilterChain defaultSecurityFilterChain` in `class SecurityFilterChainConfiguration` in `SpringBootWebSecurityConfiguration`

1. Create migration `user`
2. Create: `CustomUserDetailsService implements UserDetailsService`
3. Create: `CustomAuthProvider implements AuthenticationProvider`
4. Create: `DefaultSecurityConfig`