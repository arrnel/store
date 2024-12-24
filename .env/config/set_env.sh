#!/bin/bash

# Указание файла конфигурации
CURRENT_DIR="$(cd "$(dirname "$0")" && pwd)"
CONFIG_FILE="$CURRENT_DIR/config.env"
ZSHRC_FILE="$HOME/.zshrc"
TEMP_FILE="$HOME/.zshrc.backup"

# Проверка существования файла конфигурации
if [[ ! -f "$CONFIG_FILE" ]]; then
    echo "Файл конфигурации $CONFIG_FILE не найден!"
    exit 1
fi

# Создание временного файла для модификации
cp $ZSHRC_FILE $TEMP_FILE

# Чтение файла конфигурации и добавление переменных
while IFS= read -r line; do
    # Пропуск пустых строк и комментариев
    [[ -z "$line" || "$line" =~ ^# ]] && continue

    # Извлечение ключа и значения
    key=$(echo "$line" | cut -d'=' -f1)
    value=$(echo "$line" | cut -d'=' -f2-)

    # Добавление в начало, если переменная отсутствует
    if ! grep -q "^export $key=" "$ZSHRC_FILE"; then
        echo "Добавляю $key с значением $value"
    else
        echo "Переменная $key уже существует в $ZSHRC_FILE"
    fi
done < "$CONFIG_FILE"

# Добавление старого содержимого файла после новых переменных
cat "$ZSHRC_FILE" >> "$TEMP_FILE"

# Замена старого файла новым
mv "$TEMP_FILE" "$ZSHRC_FILE"

# Подтверждение результата
echo "Все переменные окружения из $CONFIG_FILE добавлены в начало $ZSHRC_FILE."
source $ZSHRC_FILE
