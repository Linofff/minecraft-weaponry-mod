cd /home/fonil/minecraftmoding/militarymod || exit

./gradlew build

if [ $? -eq 0 ]; then
    echo "Build success! Moving mod..."

    rm -rf /home/fonil/.local/share/PrismLauncher/instances/1.21.1/minecraft/mods/*

    mv build/libs/* /home/fonil/.local/share/PrismLauncher/instances/1.21.1/minecraft/mods/

		/nix/store/bq8zi2zwhphddbh8gwpsb7m334qzp053-prismlauncher-unwrapped-10.0.2/bin/prismlauncher --launch 1.21.1

    echo "Done."
else
    echo "Build FAILED. Files not moved."
    exit 1
fi
