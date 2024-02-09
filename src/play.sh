javac Main.java
if [ $? -eq 0 ]; then
    java Main
else
    echo "Ошибка компиляции. Пожалуйста, устраните ошибки и повторите попытку."
fi
