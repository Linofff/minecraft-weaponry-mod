cd /home/fonil/minecraftmoding/militarymod || exit

./gradlew runData
./gradlew build

if [ $? -eq 0 ]; then
    echo "Build success! Moving mod..."

    rm -rf /home/fonil/.local/share/PrismLauncher/instances/1.21.1/minecraft/mods/militarymod-1.0.0.jar

    mv build/libs/* /home/fonil/.local/share/PrismLauncher/instances/1.21.1/minecraft/mods/

    echo "Done."
else
    echo "Build FAILED. Files not moved."
    exit 1
fi
